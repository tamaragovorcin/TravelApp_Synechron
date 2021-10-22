import React, { useContext, useEffect, useState } from "react";
import Paper from "@material-ui/core/Paper";
import Tab from "@material-ui/core/Tab";
import Tabs from "@material-ui/core/Tabs";
import ReserveTransferDetails from "./tabComponents/ReserveTransferDetails.jsx"

import { DataContext } from "../contexts/DataContext";
const ReserveTransferForm = () => {
	const { dataState, dispatch } = useContext(DataContext);
	const [value, setValue] = React.useState(0);

	return (
		<React.Fragment>

			<div>
				<Paper square>
					<Tabs
						value={value}
						textColor="primary"
						indicatorColor="primary"
					>
						<Tab label="Reserve Transfer" />
					</Tabs>
					{value === 0 &&
						<ReserveTransferDetails/>	
					}

				</Paper>
			</div>
		</React.Fragment>
	);
};

export default ReserveTransferForm;