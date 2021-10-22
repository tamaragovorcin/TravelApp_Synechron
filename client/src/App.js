import "./App.css";
import { HashRouter as Router, Switch, Route, Redirect } from "react-router-dom";
import LoginPage from "./pages/LoginPage.jsx";
import HomePage from "./pages/HomePage.jsx";
import TransferPage from "./pages/TransferPage";
import PageNotFound from "./pages/PageNotFound.jsx";
import { ProtectedRoute } from "./router/ProtectedRouter.jsx";
import UnauthorizedPage from "./pages/UnauthorizedPage.jsx";

function App() {
	return (
		<Router>
			<Switch>
				<Route exact path="/" component={HomePage} />
				<Route exact path="/transfer" component={TransferPage} />
				<ProtectedRoute roles={[]} redirectTo="/" path="/login" component={LoginPage} />
				<Route path="/unauthorized" component={UnauthorizedPage} />

				<Route path="/404" component={PageNotFound} />
				<Redirect to="/404" />
			</Switch>
		</Router>
	);
}

export default App;
