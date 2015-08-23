# tower

Environment setup notes

1. Install eclipse 64-bit from eclipse.org - I am using Version: Mars Release (4.5.0), 64-bit
	Download here: https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/mars/R/eclipse-jee-mars-R-win32-x86_64.zip

2. Install git for your operating system - IF YOU ARE ON WINDOWS follow these instructions in detail
	Install from https://git-scm.com/download/win
	Hit Next on welcome screen
	Next on license screen
	Next on Select Components (default is ok)
	Select "Use git and optional Unix tools..." (the last choice) unless you already have good unix tools, or you are on a mac.  
	IMPORTANT: on the next screen, be sure to choose "Checkout windows-style, commit Unix-style line endings".  On a mac, just choose the top choice.
	Hit Next

3. Test git command line - open a command prompt
	git (press enter - should see help)

3.5 Install github gui from github https://desktop.github.com/

4. Configure git command line
	git config --global user.email "you@example.com"
	git config --global user.name "Your Name"
	git config --global push.default simple

5. Checkout tower
   Open command prompt
   cd Document
   cd GitHub (do mkdir GitHub if it doesn't exist yet, and then cd to it)
   git clone 

6. Install java JDK: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
	Install Windows x64

7. Open eclipse - import the gradle project for tower

8. Install the tile editor used for creating maps from http://www.mapeditor.org/download.html
