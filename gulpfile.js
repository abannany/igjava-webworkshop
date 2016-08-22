'use strict'

/**
 * Gulp tasks for the demonstration
 */
var gulp = require('gulp');
var del = require('del');
var sass = require('gulp-sass');
var notify = require('gulp-notify');

var paths = {
    nodeDir: "./node_modules",
    assets: {
        bootstrap: "node_modules/bootstrap/dist",
        bootstrapsass: "node_modules/bootstrap-sass/assets"
    },
    src: {
        style: "src/resources/style"
    },
};

/** Default task.
 * This one is called when gulp is exucuted without arguments.
 */
gulp.task('default',['sass'], function () {
    gulp.src(["index.html",
        paths.assets.bootstrap + "/**/*.css",
        paths.assets.bootstrap + "/**/*.css.map"])
        .pipe(gulp.dest("dist"));
});

gulp.task('sass',function(){
    gulp.src('src/app/style/style.scss')
        .pipe(sass(sass({
            style: 'compressed',
            loadPath: [
                paths.nodeDir + '/bootstrap-sass/assets/stylesheets'
            ]
        }) ).on("error", notify.onError(function (error) {
        return "Error: " + error.message;})))
        .pipe(gulp.dest('dist/css'));
});

/**
 * Cleanup dist folder.
 */
gulp.task('clean', function (cb) {
    return del(["dist"], cb);
});