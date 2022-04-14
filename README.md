# Welcome to my Branch 😊

This readme outlines everything you need to know about this branch.
> Right-click README.md and hit "Open Preview"

## Navbar
<hr>
The biggest addition is the navbar, which is fully functional and changes from page to page.
I added in "about", "contact", "home", and "help" pages, but that is only based on the phase 1 design, and we may not end up implementing those.
<br> <br>
Instead of changing scene for every page, this layout uses a Vbox containing the navbar and the current page.
<br>
This is an example of what changing pages would look like:

```java
currentPage.getChildren().setAll(signInMainBorderPane);
```

## Constants
There are a few constants added to the code for readabillity. Notably the URL's are declared early in the program.

## Renamed Images
The image files were renamed because they were quite long 😅

## New Listener
A listener based on this layout replaces the scene-based listener. They operate in the same way. (Line 860)

## Moved stuff
Some stuff might be moved around compared to the main branch but its probably there. Almost nothing was deleted.

## Semantics
I referred to the sign-in page as the log-in page so might create some subtle differences.

## Buttons
I left the nav buttons in from the scene-based design, and they should be functional. Not sure if we will still need them though.

## Test User
Instead of 'd' to log into the page I changed it to "DEFAULT" for both the user and password.

## Errors
I ran into a few unexplained, unfixed errors when logging in and switching to cart. Not sure about what is causing it.