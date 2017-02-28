### Minify with gulp

* Use gulp-minify. See (https://www.npmjs.com/package/gulp-minify)

Usage:

```javascript
var minify = require('gulp-minify');
 
gulp.task('compress', function() {
  gulp.src('lib/*.js')
    .pipe(minify({
        ext:{
            src:'-debug.js',
            min:'.js'
        },
        exclude: ['tasks'],
        ignoreFiles: ['.combo.js', '-min.js']
    }))
    .pipe(gulp.dest('dist'))
})
```