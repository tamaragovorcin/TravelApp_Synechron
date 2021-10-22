import React from "react";
import Tab from "@material-ui/core/Tab";
import Tabs from "@material-ui/core/Tabs";
import Paper from "@material-ui/core/Paper";

const NavigationTab = () => {

	const [value, setValue] = React.useState(2);

     const loadTravels = () => {
	 	window.location = "#/";
     };

     const loadTransfers = () => {
		window.location = "#/transfer";
	};

	return (
		<React.Fragment>

			<div>
            <Paper square>
					<Tabs
                        centered={true}
						value={value}
						textColor="primary"
						indicatorColor="primary"
						onChange={(event, newValue) => {
							setValue(newValue);
						}}
					>
						<Tab label="Travels" />
						<Tab label="Transfers" />
					</Tabs>

					{value === 0 &&
						loadTravels()
					}
					{value === 1 &&
                        loadTransfers()
					} 
                </Paper>
			</div>
		</React.Fragment>
	);
};

export default NavigationTab;