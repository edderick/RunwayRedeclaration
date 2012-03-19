I re-jiggled the folder structure to a simpler structure so the controller could easily tie the model and the view together and not fudge up the code too much for those guys.

=================================================================================================================
Instruction to import the projects into eclipse
=================================================================================================================
(These instructions are for importing into an empty project)
1. ctrl+click the folders 'lib', 'model' and 'view' selecting the three folders.
2. ctrl+c
3. In eclipse, package explorer, click on the src folder of the project in order to highlight it.
4. ctrl+v, eclipse should now import the folders as packages.
5. Expand the lib package and highlight all the imports in that folder (using ctrl+click or shift+click).
6. Right click > Build Path > Add to Build Path. Then all the errors should go away.
7. Now expand the package you want and work :)


=================================================================================================================
Instruction to put stuff onto the svn
=================================================================================================================
1. In eclipse, package explorer, expand the src folder of the project.
2. ctrl+click the folders 'lib', 'model' and 'view' selecting the three folders. 
3. ctrl+c
4. Go to the src folder of the svn
5. ctrl+v and overwrite everything when prompted by your prefered OS.
6. Commit.