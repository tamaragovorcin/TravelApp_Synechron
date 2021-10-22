/*export function authHeader() {
	validateAccessToken();
	let token = localStorage.getItem("accessToken");

	if (token) {
		return { Authorization: "Bearer " + token };
	} else {
		return {};
	}
}*/

export function authHeader() {

	if (localStorage.getItem("expireTime") <= new Date().getTime()) {
		localStorage.removeItem("accessToken");
		localStorage.removeItem("roles");
		localStorage.removeItem("expireTime");
	}

	return `Bearer ${localStorage.getItem("accessToken")}`;
}

export function setAuthInLocalStorage(data) {

	console.log(data)

	if(data.expires === "godina"){

		localStorage.setItem("accessToken", data.accessToken);
		localStorage.setItem("roles", JSON.stringify(data.roles));
		localStorage.setItem("expireTime", new Date(new Date().getTime() + 31556952000).getTime());


	} else{
		localStorage.setItem("accessToken", data.accessToken);
		localStorage.setItem("roles", JSON.stringify(data.roles));
		localStorage.setItem("expireTime", new Date(new Date().getTime() + Number(data.expires)).getTime());

	}

}


export function hasRoles(desiredRoles) {
	validateAccessToken();
	let roles = JSON.parse(localStorage.getItem("roles"));
	let retVal = false;

	if (roles) {
		roles.forEach((role) => {
			desiredRoles.forEach((desiredRole) => {
				if (desiredRole === "*" || desiredRole === role.name) {
					retVal = true;
				}
			});
		});
	} else if (desiredRoles.length === 0) {
		retVal = true;
	}

	return retVal;
}


export function hasPermissions(desiredPermissions) {

	validateAccessToken();
	let roles = JSON.parse(localStorage.getItem("roles"));
	let retVal = false;

	if (roles) {
		roles.forEach((role) => {
			role.permissions.forEach((permission) => {
				desiredPermissions.forEach((desiredPermission) => {
					if (desiredPermission === "*" || desiredPermission === permission.name) {
						retVal = true;
					}
				});
			});
		});
	} else if (desiredPermissions.length === 0) {
		retVal = true;
	}

	return retVal;
}

function validateAccessToken() {

	let time = new Date().getTime();
	console.log(localStorage.getItem("expireTime"));
	console.log(time);
	console.log(localStorage.getItem("expireTime") <= time);

	if (localStorage.getItem("expireTime") <= new Date().getTime()) {
		deleteLocalStorage();
	}
}

export function deleteLocalStorage() {

	localStorage.removeItem("accessToken");
	localStorage.removeItem("roles");
	localStorage.removeItem("expireTime");
}
