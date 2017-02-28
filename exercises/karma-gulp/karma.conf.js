
module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine', 'angular-filesort'],
        files: [
            'bower_components/angular/angular.js',
            'bower_components/angular-mocks/angular-mocks.js',
            'src/**/*.js',
            'tests/*Spec.js',
            'tests/**/*Spec.js'
        ],
        angularFilesort: {
            whitelist: [
                'src/**/*.js'
            ]
        },

        exclude: [],

        reporters: ['progress'],
        port: 9876,
        colors: true,
        preprocessors: {},
        logLevel: config.LOG_INFO,
        autoWatch: true,

        browsers: ['Chrome_without_security'],

        customLaunchers: {
            Chrome_without_security: {
                base: 'Chrome',
                flags: ['--disable-web-security']
            }
        },

        singleRun: false,
        captureTimeout: 60000
    });
};