import { Guid } from '../entities/guid';
import { FileAttached } from './FileAttached';

/**
 * Represents an UploadQueue
 */
export class UploadQueue {
  title: string;
  fileAttached: FileAttached;
  description:string;
  file: File;
  progress: number;
  message: string;
  isCancel: boolean;
  isError: boolean;
  get isSuccess(): boolean {
    if (this.progress == 100)
      return true;

    return false;
  };

  
  UploadQueue() {}

  constructor(file?: File, title?: string, progress? : number, description?: string) {
    this.title = title;
    this.file = file;
    this.progress = progress;
    this.message = '';
    this.isCancel = false;
    this.isError = false;
    this.description = description;
  }
 
}
