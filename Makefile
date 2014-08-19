# TextKey Makefile
.PHONY: test install clean docs prepare deploy

install:
	sudo mvn install

test:
	mvn test
	
clean:
	sudo rm -rf target

docs:
	mvn javadoc:javadoc

prepare:
	sudo mvn release:clean release:prepare
 
deploy:
	sudo mvn release:perform
