# FILE STORAGE REST SERVICE


This service implements a smaller part of the described functionality.
At the moment, we do not save the actual contents of the files, only their name and size at the moment.
This service supports the following features:
- Upload
- Delete file
- Assign tags to file
- Remove tags from file
- List files with pagination optionally filtered by tags

One of the main advantages of the service is:
- opportunity at the upload automatically add tag "audio" / "video" / "document" / "image" etc. based on extension
- opportunity in the listing endpoint handle optional parameter q that will apply a search over file name. I.e. if you pass GET /file?q=aaa this will yield files "aaaaaaa.txt", "bbbb aaa ccc.zip", "AaAA.mp3" etc.
