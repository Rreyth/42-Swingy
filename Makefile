all: build

build:
	mvn -f Swingy clean package

install:
	mvn -f Swingy install

gui:
	java -jar Swingy/target/Swingy-1-jar-with-dependencies.jar gui

console:
	clear -x
	java -jar Swingy/target/Swingy-1-jar-with-dependencies.jar console

run:
	clear -x
	java -jar Swingy/target/Swingy-1-jar-with-dependencies.jar

clean:
	mvn -f Swingy clean

fclean: clean

re: fclean all

PHONY: all build clean fclean gui console re
