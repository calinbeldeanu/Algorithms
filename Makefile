build:
	mkdir -p bin/
	javac -d bin tema1/points/*.java tema1/adn/*.java tema1/stropitori/*.java

clean:
	rm -rf bin/
	
run-p1:
	java -cp bin/ points.Points
	
run-p2:
	java -cp bin/ adn.Adn
	
run-p3:
	java -cp bin/ stropitori.Stropitori