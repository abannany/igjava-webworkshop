'use strict'

/**
 * Gulp tasks for the demonstration
 */
var gulp = require('gulp');
var del = require('del');

var paths = {
    assets: {
        bootstrap: "node_modules/bootstrap/dist"
    },
    src: {
        style: "src/resources/style"
    }
};

/** Default task.
 * This one is called when gulp is exucuted without arguments.
 */
gulp.task('default', function () {
    gulp.src(["index.html",
        paths.assets.bootstrap + "/**/*.css",
        paths.assets.bootstrap + "/**/*.css.map"])
        .pipe(gulp.dest("dist"));
});

/**
 * Cleanup dist folder.
 */
gulp.task('clean', function (cb) {
    return del(["dist"], cb);
});