package fr.dawan.GestionFanClubFrontmvc.FormBeans;



	import jakarta.validation.constraints.Email;
	import jakarta.validation.constraints.NotEmpty;

	import com.fasterxml.jackson.annotation.JsonIgnore;

	public class UserForm {
		
		private int id;
		@NotEmpty
		private String firstName;
		@NotEmpty
		private String lastName;
		@Email
		private String email;
		@NotEmpty
		private String password;
		private boolean admin;
		
		private String imagePath;
		
		@JsonIgnore
		private String imageBase64;
		
		

		public boolean isAdmin() {
			return admin;
		}

		public void setAdmin(boolean admin) {
			this.admin = admin;
		}

		public String getImageBase64() {
			return imageBase64;
		}

		public void setImageBase64(String imageBase64) {
			this.imageBase64 = imageBase64;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public UserForm(int id, String firstName, String lastName, String email, String password, String imagePath) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.imagePath = imagePath;
		}

		public UserForm(String firstName, String lastName, String email, String password, String imagePath) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.imagePath = imagePath;
		}

		public UserForm() {
			super();
		}

	}



