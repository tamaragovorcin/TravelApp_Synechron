import React from "react";
import Header from "../components/Header";
import TransferInformation from "../components/TransferInfo/TransferInformation";
import NavigationTab from "../components/NavigationTab";
import DataContextProvider from "../contexts/DataContext";
import DataModalTransfer from "../components/modals/DataModalTransfer.jsx";
import DataModalReserveTransfer from "../components/modals/DataModalReserveTransfer.jsx";



const TransferPage = () => {
    return (
        <div>
            <DataContextProvider>
                    <Header />
                    <NavigationTab />
                    <TransferInformation />
                    <DataModalTransfer />
                    <DataModalReserveTransfer/>
            </DataContextProvider>
        </div>
    );
};

export default TransferPage;