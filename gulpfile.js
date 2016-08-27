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
        bootstrap: {
            sass: "node_modules/bootstrap-sass/assets/stylesheets",
            fonts: "node_modules/bootstrap-sass/assets/fonts/"
        },
        fontawesome: {
            fonts: "node_modules/font-awesome/fonts/",
            sass: "node_modules/font-awesome/scss"
        }
    },
    src: {
        style: "src/resources/style"
    },
};

/** Default task.
 * This one is called when gulp is exucuted without arguments.
 */
gulp.task('default',['sass'], function () {
    gulp.src(["index.html"])
        .pipe(gulp.dest("dist"));
});

gulp.task('sass',['fonts'],function(){
    gulp.src('src/app/style/style.scss')
        .pipe(sass(sass({
            style: 'compressed',
            loadPath: [
                './src/app/style',
                paths.assets.bootstrap.sass,
                paths.assets.fontawesome.sass
            ]
        }) ).on("error", notify.onError(function (error) {
        return "Error: " + error.message;})))
        .pipe(gulp.dest('dist/css'));
});


gulp.task('fonts',function(){
    gulp.src(paths.assets.fontawesome.fonts+"**.*")
        .pipe(gulp.dest("dist/fonts/"));
});

/**
 * Cleanup dist folder.
 */
gulp.task('clean', function (cb) {
    return del(["dist"], cb);
});