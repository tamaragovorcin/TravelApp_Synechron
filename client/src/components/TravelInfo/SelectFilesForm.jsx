
import React, { useContext, useState, useEffect } from "react";
import { filesService } from "../../services/FilesService";
import { FilesContext } from "../../contexts/FilesContext";

const SelectFilesForm = (props) => {
	const { filesState, dispatch } = useContext(FilesContext);

	const [selectedFiles, setSelectedFiles] = useState([]);
	const [selectedPdfFiles, setSelectedPdfFiles] = useState([]);
    const [selectedFileError, setSelectedFileError] = useState("none");
	const [selectedPdfFileError, setSelectedPdfFileError] = useState("none");

	const [emptyFilesList, setEmptyFilesList] = useState(true);
	const [emptyPdfFilesList, setEmptyPdfFilesList] = useState(true);

	const handlePdfFileChange = (event) => {
		event.preventDefault();

		let numberOfFiles = event.target.files.length

		if (numberOfFiles > 1) {
			for (let i = 0; i < numberOfFiles; i++) {
				addToSelectedPdfList(i);
			}
		} else {
			addToSelectedPdfList(0);
		}

		function addToSelectedPdfList(i) {
			if (event.target.files[i].type === 'application/pdf') {
				let selectedFileSimple = {
					file: event.target.files[i],
					name: event.target.files[i].name,
					fileUrl: URL.createObjectURL(event.target.files[i])
				};
				setSelectedPdfFiles(selectedPdfFiles.concat(selectedFileSimple));
				setSelectedFileError("none")
				setSelectedPdfFileError("none")
			}
			else {
				setSelectedFileError("none")
				setSelectedPdfFileError("initial")
			}
		}
		setEmptyPdfFilesList(false);

	};

	const handlePdfFileSubmit = (e) => {
		e.preventDefault();
		filesService.fileSubmit(selectedPdfFiles, props.travelReqID, dispatch);
		setSelectedPdfFiles([]);
		setEmptyPdfFilesList(true)
	};

	const selectFile = (event) => {

		let numberOfFiles = event.target.files.length
		if (numberOfFiles > 1) {
			for (let i = 0; i < numberOfFiles; i++) {
				addToSelectedList(i);
			}
		} else {
			addToSelectedList(0);
		}

		function addToSelectedList(i) {
			if (event.target.files[i].type !== 'application/pdf') {
				let selectedFileSimple = {
					file: event.target.files[i],
					name: event.target.files[i].name,
					fileUrl: URL.createObjectURL(event.target.files[i])
				};
				setSelectedFiles(selectedFiles.concat(selectedFileSimple));
				setSelectedFileError("none")
				setSelectedPdfFileError("none")

			} else {
				setSelectedFileError("initial")
				setSelectedPdfFileError("none")
			}
		}
		setEmptyFilesList(false);

	}
	const upload = () => {
		filesService.fileSubmit(selectedFiles, props.travelReqID, dispatch);
		setSelectedFiles([]);
		setEmptyFilesList(true)

	}
	useEffect(() => {
		filesService.getAllFiles(props.travelReqID, dispatch);
	}, [])

	const fileClicked = (oneFile) => {
		filesService.getFile(oneFile.name, dispatch);
	}

	const handleRemoveFile = (fileName) => {
		filesService.removeFile(fileName, dispatch);
	}

	const changeBackgroundColor = (e) => {
		e.target.style.background = "#ADD8E6";
	}

	const changeBackgroundColorLeave = (e) => {
		e.target.style.removeProperty('background');
	}

	return (
		<React.Fragment>
			<div >

				<div className="card" class=" border border-primary" >
					<div className="card-header">
						Previously uploaded files
					</div>
					<ul className="list-group list-group-flush">
						{filesState.listFiles.files && filesState.listFiles.files.map((oneFile, index) => (
							<div class="row">
								<div class="col-8">
									<li className="list-group-item" key={index} onClick={() => fileClicked(oneFile)} onMouseOver={changeBackgroundColor} onMouseLeave={changeBackgroundColorLeave}>
										{oneFile.name}
									</li>
								</div>
								<div class="col-4">

									<button className="btn btn-primary"
										onClick={() => handleRemoveFile(oneFile.name)}
									>
										Remove
									</button>
								</div>
							</div>

						))}
					</ul>
				</div>
				<div class="border border-primary ">
					<div className="card-header">
						Upload pdf file
					</div>
					<label className="btn btn-default">
						<input type="file" onChange={handlePdfFileChange} multiple />
					</label>

					<button className="btn btn-primary"
						disabled={emptyPdfFilesList}
						onClick={handlePdfFileSubmit}
					>
						Upload
					</button>

					<div className="card">
						<div className="text-danger" style={{ display: selectedPdfFileError }}>
							File type must be pdf!
						</div>
                    </div>

					<div className="card">
						<div className="card-header">
							List of pdf Files
						</div>
						<ul className="list-group list-group-flush">
							{selectedPdfFiles && selectedPdfFiles.map((oneFile, index) => (
								<li className="list-group-item" key={index}>
									<a href={oneFile.fileUrl} download>{oneFile.name}</a>
								</li>
							))}
						</ul>
					</div>
				</div>
				<div class=" border border-primary">
					<div className="card-header">
						Upload file (not pdf)
					</div>
					<label className="btn btn-default">
						<input type="file" onChange={selectFile} multiple />
					</label>

					<button className="btn btn-primary"
						disabled={emptyFilesList}
						onClick={upload}
					>
						Upload
					</button>

					<div className="card">
						<div className="text-danger" style={{ display: selectedFileError }}>
							Please upload file with valid type!   
						</div>
                    </div>

					<div className="card">
						<div className="card-header">
							List of Files
						</div>
						<ul className="list-group list-group-flush">
							{selectedFiles && selectedFiles.map((oneFile, index) => (
								<li className="list-group-item" key={index}>
									<a href={oneFile.fileUrl} download>{oneFile.name}</a>
								</li>
							))}
						</ul>
					</div>
				</div>
			</div>
		
		</React.Fragment>

	);

}


export default SelectFilesForm;