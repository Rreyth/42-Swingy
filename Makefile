all: build

build:
	mvn -f Swingy clean package

gui:
	java -jar Swingy/target/Swingy-1.jar gui

console:
	java -jar Swingy/target/Swingy-1.jar console

clean:
	mvn -f Swingy clean

fclean: clean

PHONY: all build clean fclean gui console