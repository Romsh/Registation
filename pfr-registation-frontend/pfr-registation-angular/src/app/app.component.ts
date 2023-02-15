import {Component} from '@angular/core';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import {UploadFileServiceService} from './services/upload-file-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'pfr-registation-angular';

  selectedFiles: FileList;

  currentFileUplolad: File;
  progress: {percentage: number} = {percentage: 0 };
  selectedFile = null;
  changeImage = false;
  constructor(private uploadFileService: UploadFileServiceService) {
  }
  downloadFile(): void{
    const link = document.createElement('a');
    link.setAttribute('target', '_blank');
    link.setAttribute('href', 'File_Saved_Path');
    link.setAttribute('download', 'file_name.pdf');
    document.body.appendChild(link);
    link.click();
    link.remove();
  }
  change($event): void {
    this.changeImage = true;
}
  changedImage(event): void{
    this.selectedFile = event.target.files[0];
  }
  upload(): void{
    this.progress.percentage = 0;
    this.currentFileUplolad = this.selectedFiles.item(0);
    this.uploadFileService.pushFileStorage(this.currentFileUplolad).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        alert('document sauvegardé avec succès');
      }
      this.selectedFiles = undefined;
    });
  }
    selectFile(event): void{
    this.selectedFiles = event.target.files;
    }
}
