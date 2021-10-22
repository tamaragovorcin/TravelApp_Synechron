import { hasRoles } from "../helpers/auth-header";
import { userService } from "../services/UserService";

const Header = () => {
	
	const navStyle = { height: "50px", borderBottom: "1px solid rgb(200,200,200)" };
	const iconStyle = { fontSize: "30px", margin: "0px", marginLeft: "13px" };

	const backToHome = () => {
		window.location = "#/";
	};

	return (
		<nav className="navbar navbar-light navbar-expand-md navigation-clean" style={navStyle}>
			<div className="container">
				<div>
					<img onClick={() => backToHome()} src="assets/img/synechron.png" alt="Synechron logo" style = {{width: 100 , resizeMode: 'contain'}}/>
				</div>
				<button className="navbar-toggler" data-toggle="collapse">
					<span className="sr-only">Toggle navigation</span>
					<span className="navbar-toggler-icon"></span>
				</button>
				<div className="d-flex align-items-center">
					

					<i className="fa fa-home" style={iconStyle} />
					<div className="dropdown" hidden={!hasRoles(["*"])}>
						<i className="fa fa-user" style={iconStyle} id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" />

						<ul style={{ width: "200px", marginLeft: "15px" }} className="dropdown-menu" aria-labelledby="dropdownMenu1">
							
							<li>
								<button className=" btn shadow-none" onClick={() => userService.logout()}>
									Logout
								</button>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
	);
};

export default Header;
