import { createRequire } from "node:module";
import { dirname, join } from "node:path";

import type { App, BrowserWindowConstructorOptions } from "electron";


async function main() {
  await inject();

  console.log("[Bleeper] Bleeper DEV, made by AlphaKR93");

  console.log("[Bleeper] Starting Beeper");
  await import(BeeperTexts.filename);
}

async function inject() {
  console.log("Loading dependencies...");

  if (!BeeperTexts || !BeeperTexts.main) {
    throw new Error("Electron application module is not injected, or not an electron app");
  }

  const origAppPath = join(BeeperTexts.dirname, "..", BeeperTexts.dirname.endsWith("app") ? "_app" : "app");
  const beeper = (await import(join(origAppPath, "package.json"), {with: {type: "json"}})).default;
  BeeperTexts.dirname = dirname(origAppPath);
  BeeperTexts.filename = join(origAppPath, beeper.main);

  const __require = createRequire(BeeperTexts.url);
  const electron = __require("electron");
  const { app }: { app: App; } = electron;
  app.setVersion(beeper.version);
  app.setAppPath(origAppPath);

  if (process.argv.includes("--safe-mode")) {
    console.warn("Running in safe mode. Bleeper will not be loaded.");
    return;
  }

  class BrowserWindow extends electron.BrowserWindow {
    constructor(options: BrowserWindowConstructorOptions) {
      if (!(options?.webPreferences?.preload)) {
        super(options);
        return;
      }

      const original = options.webPreferences.preload;
      options.webPreferences.preload = join(import.meta.dirname, "preload.js");
      options.webPreferences.sandbox = false;
      // work around discord unloading when in background
      options.webPreferences.backgroundThrottling = false;

      // if (settings.frameless) {
      //   options.frame = false;
      // } else if (process.platform === "win32" && settings.winNativeTitleBar) {
      //   delete options.frame;
      // }
      options.frame = false;

      if (true /* settings.transparent */) {
        options.transparent = true;
        options.backgroundColor = "#00000000";
      }

      // if (settings.disableMinSize) {
      //   options.minWidth = 0;
      //   options.minHeight = 0;
      // }

      // const needsVibrancy = process.platform === "darwin" && settings.macosVibrancyStyle;
      //
      // if (needsVibrancy) {
      //   options.backgroundColor = "#00000000";
      //   if (settings.macosVibrancyStyle) {
      //     options.vibrancy = settings.macosVibrancyStyle;
      //   }
      // }

      process.env.__PRELOAD = original;
      super(options);
      // if (settings.disableMinSize) {
      //   // Disable the Electron call entirely so that Discord can't dynamically change the size
      //   this.setMinimumSize = (width: number, height: number) => { };
      // }
    }
  }
  Object.assign(BrowserWindow, electron.BrowserWindow);
  // esbuild may rename injected BrowserWindow class, which leads to it being excluded from getFocusedWindow()
  // See: https://github.com/electron/electron/blob/v34.2.0/lib/browser/api/browser-window.ts#L101
  Object.defineProperty(BrowserWindow, "name", {value: "BrowserWindow", configurable: true});

  // Inject electron exports with our custom BrowserWindow
  const electronPath = __require.resolve("electron");
  delete __require.cache[electronPath]!.exports;
  __require.cache[electronPath]!.exports = {
    ...electron,
    BrowserWindow,
  };

  // Monkey patch commandLine to:
  // - disable WidgetLayering: Fix DevTools context menus https://github.com/electron/electron/issues/38790
  // - disable UseEcoQoSForBackgroundProcess: Work around Discord unloading when in background
  const originalAppend = app.commandLine.appendSwitch;
  app.commandLine.appendSwitch = function (...args) {
    if (args[0] === "disable-features") {
      const disabledFeatures = new Set((args[1] ?? "").split(","));
      disabledFeatures.add("WidgetLayering");
      disabledFeatures.add("UseEcoQoSForBackgroundProcess");
      args[1] += [...disabledFeatures].join(",");
    }
    return originalAppend.apply(this, args);
  };

  // disable renderer backgrounding to prevent the app from unloading when in the background
  // https://github.com/electron/electron/issues/2822
  // https://github.com/GoogleChrome/chrome-launcher/blob/5a27dd574d47a75fec0fb50f7b774ebf8a9791ba/docs/chrome-flags-for-tools.md#task-throttling
  // Work around discord unloading when in background
  // Discord also recently started adding these flags but only on windows for some reason dunno why, it happens on Linux too
  app.commandLine.appendSwitch("disable-renderer-backgrounding");
  app.commandLine.appendSwitch("disable-background-timer-throttling");
  app.commandLine.appendSwitch("disable-backgrounding-occluded-windows");
}

await main();
