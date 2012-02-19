Klout Java Library.
=================

A simplistic Klout library. Currently the library only retrieves Klout scores for a
given user. Futher functionality may be added at a later date if I require it.
Feel free to fork!

aidanchurch@gmail.com

Getting Started
----------------

The following is how to get klout scores using this library...


        Klout k = new Klout(API_KEY);
		List<String> user = new ArrayList<String>();
		user.add("aidanchurch");
		try {
			List<User> users = k.getUsersScores(user);
			for(User u : users){
				System.out.println("Name: " + u.getUname() + " Klout Score: " + u.getKScore());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KloutException e) {
			e.printStackTrace();
		}