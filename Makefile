all: build

build:
	mvn clean package

install:
	mvn install

gui:
	java -jar target/Swingy-1-jar-with-dependencies.jar gui

console:
	clear -x
	java -jar target/Swingy-1-jar-with-dependencies.jar console

clean:
	mvn clean

fclean: clean

re: fclean all

PHONY: all build clean fclean gui console re
