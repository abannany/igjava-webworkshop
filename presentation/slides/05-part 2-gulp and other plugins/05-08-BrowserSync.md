
### BrowserSync

Install

```bash
    npm install browser-sync gulp --save-dev
```

```javascript

var gulp = require('gulp');
var browserSync = require('browser-sync');
var browserSyncSpa = require('browser-sync-spa');
var util = require('util');
var middleware = require('./proxy');

module.exports = function (options) {

    function browserSyncInit(baseDir, browser) {
        browser = browser === undefined ? 'default' : browser;
        var server = {
            baseDir: baseDir
        };
        if (middleware.length > 0) {
            server.middleware = middleware; // To manipulate res, req
        }
        browserSync.instance = browserSync.init({
            startPath: '/',
            server: server,
            browser: browser
        });
    }

    browserSync.use(browserSyncSpa({
        selector: '[ng-app]'// Only needed for angular apps
    }));

    gulp.task('serve', ['watch'], function () {
        browserSyncInit([options.tmp + '/serve', options.src]);
    });

    gulp.task('serve:dist', ['build'], function () {
        browserSyncInit(options.dist);
    });
};
```