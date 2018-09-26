import { Guid } from "../../components/entities/guid";
import { FileAttached } from "../../components/entities/FileAttached";

export class EstablishmentAttachments {
    attachment_id: Guid;
    fileName: string;
    title: string;
    description: string;
    progress: number;
    file: File;
    fileAttached: FileAttached;;
    size: number;
    link: String;
   

    constructor (file?: any, attachment_id?: Guid, fileName?: string,title?: string, description?: string,size?: number, progress? : number,fileAttached?: FileAttached, link?: String) {
        this.attachment_id = attachment_id;
        this.fileName = fileName;
        this.progress = progress;
        this.title = title;
        this.description = description;
        this.file = file;
        this.fileAttached = fileAttached;
        this.size = size;
        this.link = link;
    }
}
