all:
	javac -cp src/lib/mail.jar:src/lib/activation.jar:src/lib/miglayout15-swing.jar:.  src/View/*.java src/Model/*.java src/Controller/*.java
run:
	cd src; \
	java -cp lib/mail.jar:lib/activation.jar:lib/miglayout15-swing.jar:.  View/MainFrame

## This is very experimental ##
jar: all
	cd src; \
	echo Main-Class: View/MainFrame > manifest.txt; \
	jar cvfm SEG.jar manifest.txt Model/*.class View/*.class Controller/*.class data man lib 

clean:
	rm src/View/*.class
	rm src/Model/*.class
	rm src/Controller/*.class
