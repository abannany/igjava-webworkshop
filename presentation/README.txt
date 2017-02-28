The presentation sheets are created with 'Prez'. With Prez the sheets can be described with with html or Markdown. Underneeth 
RevealsJs is used to create the sheets. The sheets can be transformed to a PDF file.

See https://github.com/byteclubfr/prez for more info. 

To run Prez NodeJs and Npm must be installed.

This README describes a procudure to:
--Install the needed tooling
--Run the presentation
--etc.

Install prez:

> npm install -g prez

Run prez:

> prez --serve --watch

--serve: Is to run a webserver and the presentation can be used in the browser
--watch: Watches for sheet changes

Add sheets:
Add the sheets in the slides folder.

Create pdf:
Run prez en print the sheets from the browser