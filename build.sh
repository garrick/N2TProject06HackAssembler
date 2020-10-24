clear
cd hackassembler
gradle clean build --info --rerun-tasks
cd ..
echo "Running...."
java -jar hackassembler/build/libs/hackassembler-1.0-SNAPSHOT.jar pong/Pong.asm > MyPong.hack
echo "Comparing with gold master..."
echo "========================="
diff MyPong.hack pong/GoldMasterPong.hack
echo "========================="
echo "No news is good news!"
