import { context } from "esbuild";
import { execSync } from "child_process";


const WATCH = process.argv.includes("--watch");
const COMMIT_SHA = execSync("git rev-parse --short HEAD", { encoding: "utf-8" }).trim();

/**
 * Creates default build options.
 *
 * @param {import("esbuild").BuildOptions} options
 * @return {import("esbuild").BuildOptions}
 */
export function createCommonOptions(options) {
  return {
    logLevel: "info",
    bundle: true,
    minify: !WATCH,
    sourcemap: WATCH ? "inline" : "external",
    legalComments: "linked",
    banner: {
      js: `
      // Bleeper ${COMMIT_SHA}
      `.trim()
    },
    jsx: "transform",
    jsxFactory: "BleeperCreateElement",
    jsxFragment: "ReactFragment",
    inject: ["./scripts/build/_inject.mjs"],
    ...options,
    plugins: [
      ...(options.plugins ?? []),
      // fileUrlPlugin,
      // gitHashPlugin,
      // gitRemotePlugin,
      // stylePlugin
    ],
    external: [
      ...(options.external ?? []),
      // "~plugins",
      // "~git-hash",
      // "~git-remote",
      // "/assets/*"
    ],
  }
}

/**
 * Executes esbuild.
 *
 * @param options {import("esbuild").BuildOptions[]}
 * @returns {Promise<void>}
 */
export async function build(options) {
  if (WATCH) {
    await Promise.all(options.map(option => context(option).then(context => context.watch())));
    return;
  }

  await Promise.all(options.map(option => context(option).then(context => build(context))))
    .catch(e => {
      console.error(e);
      process.exit(1);
    })
}
