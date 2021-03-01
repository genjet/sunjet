const resolve = dir => require("path").join(__dirname, dir);

const port = process.env.VUE_APP_PORT;

module.exports = {
  publicPath: "/",
  outputDir: "dist",
  productionSourceMap: false,
  // 构建时开启多进程处理 babel 编译
  parallel: require("os").cpus().length > 1,
  //assetsDir: 'static',

  chainWebpack(config) {
    config.resolve.alias
      .set("@", resolve("src"))
      .set("@api", resolve("src/api"))
      .set("@assets", resolve("src/assets"))
      .set("@components", resolve("src/components"))
      .set("@directive", resolve("src/directive"))
      .set("@layout", resolve("src/layout"))
      .set("@icons", resolve("src/icons"))
      .set("@plugins", resolve("src/plugins"))
      .set("@router", resolve("src/router"))
      .set("@store", resolve("src/store"))
      .set("@styles", resolve("src/styles"))
      .set("@utils", resolve("src/utils"))
      .set("@vendor", resolve("src/vendor"))
      .set("@views", resolve("src/views"))
      .set("@locale", resolve("src/locale"))
      .set("@typings", resolve("src/typings"))
      .set("@inits", resolve("src/inits"));
    // set svg-sprite-loader
    config.module
      .rule("svg")
      .exclude.add(resolve("src/icons"))

      .end();
    config.module
      .rule("icons")
      .test(/\.svg$/)
      .include.add(resolve("src/icons"))
      .end()
      .use("svg-sprite-loader")
      .loader("svg-sprite-loader")
      .options({
        symbolId: "icon-[name]"
      })
      .end();

    // set preserveWhitespace
    config.module
      .rule("vue")
      .use("vue-loader")
      .loader("vue-loader")
      .tap(options => {
        options.compilerOptions.preserveWhitespace = true;
        return options;
      })
      .end();

    config
      // https://webpack.js.org/configuration/devtool/#development
      .when(process.env.NODE_ENV === "development", config => config.devtool("cheap-source-map"));

    config.when(process.env.NODE_ENV !== "development", configuration => {
      configuration.optimization.splitChunks({
        chunks: "all",
        cacheGroups: {
          libs: {
            name: "chunk-libs",
            test: /[\\/]node_modules[\\/]/,
            priority: 10,
            chunks: "initial"
          },
          elementUI: {
            name: "chunk-elementUI",
            priority: 20,
            test: /[\\/]node_modules[\\/]_?element-ui(.*)/
          },
          commons: {
            name: "chunk-commons",
            test: resolve("src/components"),
            minChunks: 3,
            priority: 5,
            reuseExistingChunk: true
          }
        }
      });
      configuration.optimization.runtimeChunk("single");
    });
  },
  css: {
    extract: true,
    requireModuleExtension: true,
    loaderOptions: {
      less: {
        javascriptEnabled: true,
        modifyVars: {}
      },
      sass: {}
    }
  },
  devServer: {
    // host: 'localhost',
    //host: "0.0.0.0",
    port: port, // 端口号
    https: false, // https:{type:Boolean}
    open: false, //配置自动启动浏览器  http://172.11.11.22:8888/rest/XX/
    hotOnly: true, // 热更新
    overlay: {
      warnings: false,
      errors: true
    },
    sockHost: 'http://localhost:9478/',
    disableHostCheck: true,
    public: '0.0.0.0:9478',
    proxy: {
      "/api": {
        target: "http://localhost:8078",
        changeOrigin: true
      }
    }
    //before: require("./mock/mock-server.js"),
  }
};
