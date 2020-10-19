clear
cd hackassembler
mvn clean install
cd ..
echo "Running...."
java -jar hackassembler/target/hackassembler-1.0-SNAPSHOT.jar pong/Pong.asm > MyPong.hack
echo "Comparing with gold master..."
echo "========================="
diff MyPong.hack pong/GoldMasterPong.hack
echo "========================="
echo "No news is good news!"
