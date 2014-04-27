package management;

public class CommandFactory {

	public static InterfaceCommand getCommand(String name) {

		InterfaceCommand cmd = null;
		Class c1 = null; // cualquier clase (Leer, update, etc.)

		try {
			c1 = Class.forName("foo." + name);// Package.clase
			cmd = (InterfaceCommand) c1.newInstance();//llama al constructor vacio
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cmd;
	}

}
