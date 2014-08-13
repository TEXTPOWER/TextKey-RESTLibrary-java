# TextKey Makefile
.PHONY: test install clean docs deploy

install:
	mvn install

test:
	mvn test
	
clean:
	rm -rf target

docs:
	mvn javadoc:javadoc

deploy:
	mvn clean deploy
