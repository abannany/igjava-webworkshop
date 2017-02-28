### Uglify with gulp

* Use gulp-uglify. See (https://www.npmjs.com/package/gulp-uglify)

Usage:

```javascript
var gulp = require('gulp');
var uglify = require('gulp-uglify');
var pump = require('pump'); //used to handle the error
 
gulp.task('compress', function (cb) {
  pump([
        gulp.src('lib/*.js'),
        uglify(),
        gulp.dest('dist')
    ],
    cb
  );
});
```

