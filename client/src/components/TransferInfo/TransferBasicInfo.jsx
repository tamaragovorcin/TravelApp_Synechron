import React, { useContext, useEffect,useImperativeHandle,forwardRef, useState } from "react";
import MaterialTable from "material-table";
import { DataContext } from "../../contexts/DataContext";
import DataContextProvider from "../../contexts/DataContext";
import { dataService } from "../../services/DataService";

const TransferBasicInfo  = forwardRef((props, ref) => {
  const { dataState, dispatch } = useContext(DataContext);
  const [selectedRows, setSelectedRows] = useState([])
  useImperativeHandle(
    ref,
    () => ({
        getTravelInfoBetwenDates() {
          let datesRequest = {
            startDate : props.startDate,
            endDate : props.endDate,
          };
          someFetchActionCreator(datesRequest)
        },
    }),
)


const someFetchActionCreator =(datesRequest)=> {
  
  const getTransferBasicInfoHandler = async () => {
    await dataService.findAllTransferRequests(datesRequest,dispatch);
  };
  getTransferBasicInfoHandler();
}

const editData = () => {
  
  let data = dataState.listTransferInfo.transferInfo;
  for(let i = 0 ; i < data.length; i++){
    Object.assign(data[i],  { dateTimeArrival: data[i].arrivalDate+" "+data[i].arrivalTime });
    Object.assign(data[i], { dateTimeDeparting: data[i].departureDate +" "+data[i].departureTime });
  }
  return data;
}
 
useEffect(() => {
  let datesRequest = {
    startDate : "",
    endDate : "",
  };
  someFetchActionCreator(datesRequest)
}, [dispatch]);

return (
      <div class="p-4">
        <DataContextProvider>
        <MaterialTable
            columns={[
              { title: "Name", field: "employee.firstName" },
              { title: "Surname", field: "employee.lastName" },
              { title: "Travel request ID", field: "travelReqID" },
              { title: "Departing from",field: "departingCountry", type:'string'},
              { title: "Arriving to",field: "arrivalCountry", type:'string'},
              { title: "Departure date",field: "dateTimeDeparting", type:'string'},
              { title: "Arrival date",field: "dateTimeArrival", type:'string'},
            ]}
            actions={[
              rowData => ({
                tooltip: 'Reserve transer',
                icon: ()=><button>Reserve</button>,
                onClick: () => rowData.reservationDate == null ?  props.handleReserveModal(selectedRows) : '',
              })

              /*
              rowData => ({
              icon: ()=> rowData.reservationDate == null ? 
              <button onClick = "props.handleReserveModal(rowData)">Reserve</button> : '',
              <Chip label="Not Reserved" color="error" /> : <Chip label="Reserved" color="success" />,
              onClick: () => rowData.reservationDate == null ?  props.handleReserveModal(rowData) : '',
              }),*/
              
            ]}
            data = {editData()}
            title="Transfer Information"
            onSelectionChange={(rows) => setSelectedRows(rows)}
            options={{
              actionsColumnIndex: -1,
              search : false,
              selectionProps: rowData => ({
                checked: rowData.reservationDate != null || rowData.onClick,
              }), 
              
              selection: true,
  
            }}

            localization={{
                header: {
                    actions: 'Reserved',
                },
              
            }}

            onRowClick={(event, rowData) => {props.handleShowModal(rowData)}}
          />
          
        </DataContextProvider>
    </div> 
);
});

export default TransferBasicInfo;