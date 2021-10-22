import React from "react";
import Paper from "@material-ui/core/Paper";
import Tab from "@material-ui/core/Tab";
import Tabs from "@material-ui/core/Tabs";
import EmployeeDetails from "./tabComponents/EmployeeDetails"
import TransferDetails from "./tabComponents/TransferDetails"
const EditDataForm = () => {
	const [value, setValue] = React.useState(0);

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
						<Tab label="Transfer details" />
						<Tab label="Employee details" />
					</Tabs>
					{value === 0 &&
						<TransferDetails/>
						
					}
					{value === 1 &&
						<EmployeeDetails/>
					}

				</Paper>
			</div>
		</React.Fragment>
	);
};

export default EditDataForm;