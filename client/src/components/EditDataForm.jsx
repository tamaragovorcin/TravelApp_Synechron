
import React, { useContext, useEffect, useState } from "react";
import Paper from "@material-ui/core/Paper";
import Tab from "@material-ui/core/Tab";
import Tabs from "@material-ui/core/Tabs";
import TravelDetails from "./tabComponents/TravelDetails"
import AccommodationDetails from "./tabComponents/AccommodationDetails"
import EmployeeDetails from "./tabComponents/EmployeeDetails"
const EditDataForm = () => {
	const [value, setValue] = React.useState(2);

	return (
		<React.Fragment>

			<div>
				<Paper square>
					<Tabs
						value={value}
						textColor="primary"
						indicatorColor="primary"
						onChange={(event, newValue) => {
							setValue(newValue);
						}}
					>
						<Tab label="Travel details" />
						<Tab label="Accommodation details" />
						<Tab label="Employee details" />
					</Tabs>
					{value === 0 &&
						<TravelDetails/>
						
					}
					{value === 1 &&
						<AccommodationDetails/>
					}
					{value === 2 &&
						<EmployeeDetails/>
					}

				</Paper>
			</div>
		</React.Fragment>
	);
};

export default EditDataForm;
