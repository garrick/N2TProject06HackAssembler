###Nand2Tetris Project 6
#####Hack Assembler
This is my solution to the Hack language Assembler for the
Nand2Tetris course project 6 (see https://www.nand2tetris.org/project06).  It includes a few notable aspects and 
features not required of the original project:
* Done TDD (warts and all in Git Log) - 92% class coverage
* Nifty enhanced debugging support!
* Chain Of Command Tokenizing
* Visual Regression test against "Gold Standard" Assembler output 
* Maven configuration
* Simple CI "style" build/package/regression script 

Take a look at the build.sh file for details on running.  

Disclaimer: There's a few design and feature trade offs I made.  For example - code is not multi-thread safe with respect to DebugFlag, and error handling for invalid 
source could be better.  I did this for fun and to scratch an itch I had: being a dissatisfied with the supplied Nand2Tetris 
Assembler's lack of good debugging output.

Here's a dump of --debug output for the supplied Rect.asm code:

    ====LABELS===
    INFINITE_LOOP: 23
    LOOP: 10
    ====SYMBOLS===
    address: 17
    counter: 16
              Token Name	Token ASM		Position	Token Value	Token Raw
    ========================================================================================================================
        HackCommentToken				       0	null		// This file is part of www.nand2tetris.org
        HackCommentToken				       0	null		// and the book "The Elements of Computing Systems"
        HackCommentToken				       0	null		// by Nisan and Schocken, MIT Press.
        HackCommentToken				       0	null		// File name: projects/06/rect/Rect.asm
        HackCommentToken				       0	null
        HackCommentToken				       0	null		// Draws a rectangle at the top-left corner of the screen.
        HackCommentToken				       0	null		// The rectangle is 16 pixels wide and R0 pixels high.
        HackCommentToken				       0	null
          HackValueToken	0000000000000000	       0	@0		   @0
     HackAssignmentToken	1111110000010000	       1	D=M		   D=M
         HackSymbolToken	0000000000010111	       2	@INFINITE_LOOP		   @INFINITE_LOOP
           HackJumpToken	1110001100000110	       3	D;JLE		   D;JLE
         HackSymbolToken	0000000000010000	       4	@counter		   @counter
     HackAssignmentToken	1110001100001000	       5	M=D		   M=D
          HackValueToken	0100000000000000	       6	@16384		   @SCREEN
     HackAssignmentToken	1110110000010000	       7	D=A		   D=A
         HackSymbolToken	0000000000010001	       8	@address		   @address
     HackAssignmentToken	1110001100001000	       9	M=D		   M=D
          HackLabelToken				      10	(LOOP)		(LOOP)
         HackSymbolToken	0000000000010001	      10	@address		   @address
     HackAssignmentToken	1111110000100000	      11	A=M		   A=M
     HackAssignmentToken	1110111010001000	      12	M=-1		   M=-1
         HackSymbolToken	0000000000010001	      13	@address		   @address
     HackAssignmentToken	1111110000010000	      14	D=M		   D=M
          HackValueToken	0000000000100000	      15	@32		   @32
     HackAssignmentToken	1110000010010000	      16	D=D+A		   D=D+A
         HackSymbolToken	0000000000010001	      17	@address		   @address
     HackAssignmentToken	1110001100001000	      18	M=D		   M=D
         HackSymbolToken	0000000000010000	      19	@counter		   @counter
     HackAssignmentToken	1111110010011000	      20	MD=M-1		   MD=M-1
         HackSymbolToken	0000000000001010	      21	@LOOP		   @LOOP
           HackJumpToken	1110001100000001	      22	D;JGT		   D;JGT
          HackLabelToken				      23	(INFINITE_LOOP)		(INFINITE_LOOP)
         HackSymbolToken	0000000000010111	      23	@INFINITE_LOOP		   @INFINITE_LOOP
           HackJumpToken	1110101010000111	      24	0;JMP		   0;JMP