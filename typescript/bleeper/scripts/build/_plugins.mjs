import { builtinModules } from "node:module";


/**
 * Common `renderer.js` esbuild plugins.
 *
 * @type {import("esbuild").Plugin[]}
 */
export const rendererPlugins = (() => {
  /**
   * @param filter {RegExp}
   * @param text {string}
   * @return {import("esbuild").Plugin}
   */
  function catchInvalidImports(filter, text) {
    return {
      name: "catch-invalid-imports",
      setup: build => build.onResolve({ filter }, () => ({ errors: [{ text }] })),
    };
  }

  const builtins = builtinModules
    .map(m => m.replace(/[-/\\^$*+?.()|[\]{}]/g, "\\$&"))
    .join("|");
  return [
    catchInvalidImports(
      new RegExp(`^(?:node:)?(?:${builtins})$`),
      "Attempted to import node inbuilt modules in browser code; You should use a native.ts file"
    ),
    catchInvalidImports(
      /^electron(\/.*)?$/,
      "Attempted to import electron in browser code; You should use a native.ts file"
    ),
    catchInvalidImports(
      /^react$/,
      "Attempted to import react directly; React and hooks should be imported from @webpack/common"
    ),
  ];
})();
