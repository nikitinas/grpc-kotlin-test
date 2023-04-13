// @ts-check

const path = require('path');

// @ts-check
/** @typedef {import('webpack').Configuration} WebpackConfig * */

/** @type WebpackConfig */
const config = {
  target: 'node',
  mode: 'none', // this leaves the source code as close as possible to the original (when packaging we set this to 'production')

  entry: './src/node/main.ts',
  output: {
    path: path.resolve(__dirname, 'build/node'),
    filename: 'main.js',
    chunkFormat: 'commonjs',
    clean: true
  },
  resolve: {
    // support reading TypeScript and JavaScript files, 📖 -> https://github.com/TypeStrong/ts-loader
    extensions: ['.ts', '.js'],
  },
  module: {
    rules: [
      {
        test: /\.ts$/,
        exclude: /node_modules/,
        use: [
          {
            loader: 'ts-loader'
          }
        ]
      }
    ]
  },
  devtool: 'nosources-source-map',
  infrastructureLogging: {
    level: 'log', // enables logging required for problem matchers
  },
};
module.exports = [config];
