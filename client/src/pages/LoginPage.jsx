import { React, useEffect } from "react";
import LoginForm from "../components/LoginForm";
import UserContextProvider from "../contexts/UserContext";
import { hasRoles } from "../helpers/auth-header";



const LoginPage = () => {


	useEffect(() => {
		if(hasRoles(["*"])){

			window.location = "#/";
		}
	});
	

	return (
	
			<div>
				<section className="login-clean">
					<UserContextProvider>
						<LoginForm />
					</UserContextProvider>
				</section>
			</div>


		
	);
};

export default LoginPage;
