var gulp = require('gulp');
var del = require('del');
var typescript = require('gulp-typescript');
var tscConfig = require('./tsconfig.json');
var sourcemaps = require('gulp-sourcemaps');
var tslint = require('gulp-tslint');
var browserSync = require('browser-sync');
var reload = browserSync.reload;
var changed = require('gulp-changed');
var clean = require('gulp-clean');
var dependencies = require('./dependencies.json');
var path = require('path');
var runSequence = require('run-sequence');

var distFolder = 'dist/lib';
var distLibFolder = path.join(distFolder, 'lib');
var distCssFolder = path.join(distFolder, 'css');

gulp.task('prepare-gradle', function() {
    
	// define the dist output folder
    distFolder = '../build/web-out/dist';
	distLibFolder = path.join(distFolder, 'lib');
	distCssFolder = path.join(distFolder, 'css');

	console.log("dist: " + distFolder);
	console.log("dist-lib: " + distLibFolder);
	console.log("dist-css: " + distCssFolder);

    // overwrite the output folder for the typescript result
    tscConfig.compilerOptions.out = path.join(distFolder, 'app');
});

gulp.task('clean-dist', function () {
	return gulp.src('dist', {read: false})
		.pipe(clean({force: true}));
});

gulp.task('copy:libs', function () {
	return gulp.src(dependencies.js, {cwd: "node_modules/**"})
		.pipe(changed(distLibFolder ))
		.pipe(gulp.dest(distLibFolder ));
});

gulp.task('copy:libs:css', function () {
	return gulp.src(dependencies.css, {cwd: "node_modules/**"})
		.pipe(changed(distCssFolder))
		.pipe(gulp.dest(distCssFolder));
});

gulp.task('copy:assets', function () {
	return gulp.src(["src/**/*", "!src/app/**/*.ts"])
		.pipe(changed(distFolder))
		.pipe(gulp.dest(distFolder));
});

gulp.task('tslint', function () {
	return gulp.src('src/app/**/*.ts')
		.pipe(tslint())
		.pipe(tslint.report('verbose'));
});

gulp.task('compile', function () {
	return gulp.src(['src/**/*.ts', 'typings/**/*.ts'])
		.pipe(sourcemaps.init())
		.pipe(typescript(tscConfig.compilerOptions))
		.pipe(sourcemaps.write('.'))
		.pipe(gulp.dest(distFolder));
});

gulp.task('serve', ["build"], function () {
	browserSync({
		server: {
			baseDir: 'dist'
		}
	});
	gulp.watch(['src/**/*'], ['buildAndReload']);
});


gulp.task('clean', ['clean-dist']);
gulp.task('copy', ['copy:libs', 'copy:libs:css']);
gulp.task('check', ['tslint']);
gulp.task('build', ['compile', 'copy:assets']);
gulp.task('buildAndReload', ['build'], reload);
gulp.task('default', ['build']);
gulp.task('dist', ['clean', 'copy', 'check', 'build'])
gulp.task('gradle-dist', function() {
    runSequence('prepare-gradle', 'copy', 'check', 'compile', 'copy:assets')
});

