#!/usr/bin/env node

import { rendererPlugins } from "./_plugins.mjs";
import { build, createCommonOptions } from "./_utils.mjs";


const desktopOptions = createCommonOptions({
  format: "esm",
  platform: "node",
  target: ["esnext"],
  // @ts-expect-error this is never undefined
  external: [
    "electron",
    "original-fs",
    // "~pluginNatives"
  ]
});

await build([
  // Discord Desktop main & renderer & preload
  {
    ...desktopOptions,
    entryPoints: ["src/main/patcher/index.ts"],
    outfile: "dist/patcher.js",
    plugins: [
      ...desktopOptions.plugins,
      // globNativesPlugin
    ]
  },
  createCommonOptions({
    entryPoints: ["src/main/app.ts"],
    outfile: "dist/renderer.js",
    format: "iife",
    target: ["esnext"],
    globalName: "Bleeper",
    plugins: [
      // globPlugins("discordDesktop"),
      ...rendererPlugins
    ]
  }),
  {
    ...desktopOptions,
    entryPoints: ["src/main/preload.ts"],
    outfile: "dist/preload.js"
  },
]);
