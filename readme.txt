++Version 1.1 December 2, 2014++
New features added:
-User and Group ID verification (previous version already did not allow duplicated IDs)

-User and Group creation time added; User creation time can be viewed on the User view
panel

-Last update time added to Users, can be viewed on User View Panel

-Last update button on main panel, shows the last user to make an update

>>Bug Fixes<<
-Posting messages will now update the current user view and all follower user views immediately

-Adding a following will now update the current user view immediately

-The file path to the positive words file is no longer hard coded into the program (the words are 
now hard coded instead)

-Clicking on any editable text field will now clear the field (previously was only on the post
message field)


++Version 1.0 November 23, 2014++
!!Important!!
-The positive_words.txt file is required to run the positive percentage analysis. 
Currently, the path to the file is hardcoded into the program in the 
PositiveWordsFileReader class. I had a lot of trouble getting Eclipse to recognize
the correct path so that was the solution I found. To run the anaylsis, make sure the
path for the text file is the correct local path.

Current Implementation Issues

-Tweeting a message or following a new user doesn't update windows dynamically, 
but closing and opening a new window for the user will have the updated info. This 
is the biggest issue in my mind, I spent the most time trying to fix this.

-New users and groups can only be added when a group is selected in the tree view.

-Attempting to add a new user while a user is selected adds the user to the AdminControlPanel
but doesn't add it to the tree. Selecting a user and then trying to add the new user again
results in the program saying that there is already a user with that id.

-I've never used JTrees before so the implementation is kinda rough


Design Patterns

-The Singleton pattern is implemented in a few of the larger classes

-The Observer pattern is implemented through the Users

-I thought the UserGroup would be a good place to use the Composite pattern, 
since there can be groups of groups. But in the end it didn't really make much of
a difference in the program.

-I think a better place to use Composite would be with the Users themselves,
but I already implemented Observer into Users and adding multiple interfaces
is a tricky task, so I left it as is.

-The visitor pattern is used for the anaylsis features, although I don't think
that was the best area of the program to use them. I had a tough time trying to 
think of a good way to use the visitor pattern.

-Due to the swing API I think I used AbstractFactory and Decorator patterns, but that
is just how the swing API is designed.


