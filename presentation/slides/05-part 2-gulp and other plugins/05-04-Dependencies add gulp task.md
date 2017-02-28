### Add the gulp task

```javascript
'use strict';

var gulp = require('gulp');

var $ = require('gulp-load-plugins')();
var print = require('gulp-print');

var wiredep = require('wiredep').stream;

module.exports = function(options) {
  gulp.task('inject', ['scripts', 'styles'], function () {
    var injectStyles = gulp.src([
      options.tmp + '/serve/{app,components}/**/*.css',
      '!' + options.tmp + '/serve/app/vendor.css'
    ], { read: false });


    var injectScripts = gulp.src([
      options.src + '/{app,components}/**/*.js',
      '!' + options.src + '/{app,components}/**/*.spec.js',
      '!' + options.src + '/{app,components}/**/*.mock.js'
    ])
    .pipe($.angularFilesort()).on('error', options.errorHandler('AngularFilesort'));

    var injectOptions = {
      ignorePath: [options.src, options.tmp + '/serve'],
      addRootSlash: false
    };

    var wiredepOptions = {
      directory: 'bower_components',
      exclude: [/bootstrap-sass/, /bootstrap\.css/]
    };

    return gulp.src(options.src + '/*.html')
      .pipe(print())
      .pipe($.inject(injectStyles, injectOptions))
      .pipe($.inject(injectScripts, injectOptions))
      .pipe(print(function(filepath) {
          return "injects..: " + filepath;
       }))
      .pipe(print())
      .pipe(wiredep(wiredepOptions))
      .pipe(gulp.dest(options.tmp + '/serve'));

  });
};
```