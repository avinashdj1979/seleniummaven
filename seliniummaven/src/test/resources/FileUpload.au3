ControlFocus("Open", "", "Edit1")
Sleep(3000)
ControlSetText("Open", "", "Edit1", $CmdLine[1])
Sleep(3000)
ControlClick("Open", "", "Button1")

;"D:\coderepo\seleniummaven\seliniummaven\src\test\resources\sample.txt"