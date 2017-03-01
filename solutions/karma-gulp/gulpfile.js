var gulp = require('gulp');
var Server = require('karma').Server;

/**
 * This task is used to run once
 */
gulp.task('test-once', function (done) {
    new Server({
        configFile: __dirname + '/karma.conf.js',
        singleRun: true
    }, done).start();
});

/**
 * This task is used to run all the time. When a source file is changed the tests shall be run again
 */
gulp.task('test-cont', function (done) {
    new Server({
        configFile: __dirname + '/karma.conf.js'
    }, done).start();
});

gulp.task('default', ['test-once']);