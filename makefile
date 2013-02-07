all:
	javac -cp src/lib/mail.jar:src/lib/activation.jar:src/lib/miglayout15-swing.jar:.  src/View/*.java src/Model/*.java src/Controller/*.java
run:
	cd src; \
	java -cp lib/mail.jar:lib/activation.jar:lib/miglayout15-swing.jar:.  View/MainFrame
