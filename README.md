GetInstagramAccessToken
=======================

A Java Instagram API tool to get the access_token with explicit flow.

GetInstagramAccessToken is a simple Java class for the Instagram API that allows a user to get the
access_token for accounts using the newly-required explicit flow (which allows for raised API limitations).

All a user needs to do to get this program to run is add the required information into the strings (lines 18-22).

One can acquire an auth code using the following URL format - https://api.instagram.com/oauth/authorize/?client_id=CLIENT-ID&redirect_uri=REDIRECT-URI&response_type=code

That URL will return a URL that includes ?code= followed by the code necessary on line 23.
