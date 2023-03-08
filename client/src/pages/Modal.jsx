import { useState } from "react";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";
import Formular from "../components/Formular";

export default function FormModal() {
    const [open, setOpen] = useState(false);

    return (
        <>
            <div className="addNew-button">
            <Button className="right" onClick={() => setOpen(true)}>Add Task</Button>
            </div>
            <Modal open={open} onClose={() => setOpen(false)}>
                <Box className="modal">
                    <Button
                        className="modal-close"
                        onClick={() => setOpen(false)}>
                        &times;
                    </Button>
                    <h2 id="child-modal-title">Add Task</h2>
                    <Formular data={() => setOpen(false)} />
                </Box>
            </Modal>
        </>
    );
}
