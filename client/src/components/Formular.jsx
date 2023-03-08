import React, {useState} from "react";
import {useAtom} from "jotai";
import {postData} from "../utils/apiManagement";
import {store} from "../stateManagement/Store";

const Formular = ({data}) => {
    const REACT_APP_CRUD_API = process.env.REACT_APP_CRUD_API;
    const [added, setAdded] = useAtom(store);

    const [task, setTask] = useState({
        taskName: "",
        description: "",
        taskType: "WORK",
        deadline: "",
        numberOfDays: 0,
    });

    function handle(e) {
        e.preventDefault();
        const newTask = {...task};
        newTask[e.target.id] = e.target.value;
        setTask(newTask);
    }

    async function submit(e) {
        e.preventDefault();
        postData(REACT_APP_CRUD_API, {
            taskName: task.taskName,
            description: task.description,
            taskType: task.taskType,
            deadline: task.deadline,
            numberOfDays: task.numberOfDays,
        }).then(() => {
            setAdded(!added);
            data();
        });
    }

    return (
        <form onSubmit={submit}>
            <div className="form-fields">
                <label htmlFor="taskName">Task Name</label>
                <input type="text" id="taskName" onChange={handle} required/>
            </div>

            <div className="form-fields">
                <label htmlFor="description">Description</label>
                <input
                    type="text"
                    id="description"
                    onChange={handle}
                    required
                />
            </div>

            <div className="form-fields">
                <label htmlFor="taskType">Select Task Type</label>
                <select
                    id="taskType"
                    placeholder="WORK"
                    onChange={handle}
                    required>
                    <option value="WORK">WORK</option>
                    <option value="HOME">HOME</option>
                    <option value="HOBBY">HOBBY</option>
                </select>
            </div>

            <div className="form-fields">
                <label htmlFor="deadline">Choose The DeadLine</label>
                <input type="date" id="deadline" onChange={handle} required/>
            </div>

            <div className="form-fields">
                <label htmlFor="numberOfDays">Estimated Number Of Days</label>
                <input
                    type="number"
                    id="numberOfDays"
                    onChange={handle}
                    required
                />
            </div>

            <button type="submit" className="submit-btn">
                Submit
            </button>
        </form>
    );
};

export default Formular;
