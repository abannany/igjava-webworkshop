'use strict'

/**
 * Gulp tasks for the demonstration
 */
var gulp = require('gulp');
var del = require('del');

/** Default task.
 * This one is called when gulp is exucuted without arguments.
 */
gulp.task('default',function() {
    gulp.src(["index.html",
              "node_modules/bootstrap/dist/**/*.css",
              "node_modules/bootstrap/dist/**/*.css.map"])
        .pipe(gulp.dest("dist"));
});

/**
 * Cleanup dist folder.
 */
gulp.task('clean',function(cb) {
    return del(["dist"],cb);
});