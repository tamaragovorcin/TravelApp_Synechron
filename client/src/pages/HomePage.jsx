
import { React } from "react";
import Header from "../components/Header";
import DataModal from "../components/modals/DataModal";
import UploadFilesModal from "../components/modals/UploadFilesModal";

import TravelInformation from "../components/TravelInfo/TravelInformation";
import NavigationTab from "../components/NavigationTab";
import DataContextProvider from "../contexts/DataContext";



const HomePage = () => {
	

	return (
		<div>
			<DataContextProvider>
					<Header />
					<NavigationTab />
					<TravelInformation />
					<DataModal />
					<UploadFilesModal />

			</DataContextProvider>
		</div>
	);
};

export default HomePage;

