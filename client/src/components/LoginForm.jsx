import { useContext, useState } from "react";
import { UserContext } from "../contexts/UserContext";
import { userService } from "../services/UserService";

//const Forn({prop1, prop2, ...rest}) => {} HOOKOVI
const LoginForm = () => {
	const { userState, dispatch } = useContext(UserContext);

	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");
	const [keepMeLoggedIn, setkeepMeLoggedIn] = useState(false);

	const handleSubmit = (e) => {
		e.preventDefault();

		let loginRequest = {
			username,
			password,
			keepMeLoggedIn,
		};

		userService.login(loginRequest, dispatch);
	};


	//useEffect- kao componentDidMount

	return (
		<form method="post" onSubmit={handleSubmit}>
			<h2 className="sr-only">Login Form</h2>
			<div className="illustration">
				<i className="icon ion-ios-navigate"></i>
			</div>
			<div className="form-group">
				<input className="form-control" required name="username" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)}></input>
			</div>

			<div className="form-group">
				<input className="form-control" required type="password" name="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)}></input>
			</div>
			<div className="form-group text-center" style={{ color: "red", fontSize: "0.8em" }} hidden={!userState.loginError.showError}>
				{userState.loginError.errorMessage}
			</div>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<input type="checkbox" id="keep" name="keep" value="keep" onChange={(e) => setkeepMeLoggedIn(true)}/>
			<label for="keep">&nbsp;&nbsp; Keep me logged in</label>
			<div className="form-group">
				<input className="btn btn-primary btn-block" type="submit" value="Log In" />
			</div>

		</form>
	);
};

export default LoginForm;
